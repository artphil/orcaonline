import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SelectItem, MessageService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';

import { GtinModel, BrickModel, ClassModel } from '../product.model';
import { BrickComponent } from '../brick/brick.component';
import { ClassComponent } from '../class/class.component';
import { ClassService } from '../class/class.service';
import { GtinService } from './gtin.service';
import { BrickService } from '../brick/brick.service';

@Component({
  selector: 'app-gtin',
  templateUrl: './gtin.component.html',
  styleUrls: ['./gtin.component.css']
})
export class GtinComponent implements OnInit {

  gtin: GtinModel;
  gtinBricks: SelectItem[];
  gtinClasses: SelectItem[];

  idGtin: number;

  noPopup = true;

  constructor(
    private route: ActivatedRoute,
    private gtinServices: GtinService,
    private brickServices: BrickService,
    private classServices: ClassService,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }


  ngOnInit(): void {

    this.idGtin = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.brickServices.getList()
      .then((brickList: BrickModel[]) => {
        this.gtinBricks = [];
        brickList.forEach(item => {
          this.gtinBricks.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.gtinBricks = [
          { label: 'Nenhum Brick cadastrado', value: null }
        ];
      });

    this.classServices.getList()
      .then((classList: ClassModel[]) => {
        this.gtinClasses = [];
        classList.forEach(item => {
          this.gtinClasses.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.gtinClasses = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    if (!this.idGtin) {
      this.gtin = new GtinModel();
    } else {
      this.gtinServices.getOne(this.idGtin)
        .then((gtin: GtinModel) => {
          this.gtin = gtin ? gtin : new GtinModel();
        })
        .catch(() => {
          this.gtin = new GtinModel();
        });
    }
  }

  saveGtin(form: NgForm): void {
    if (!this.idGtin) {
      this.gtinServices.create(this.gtin)
        .then((gtin: GtinModel) => {
          this.messageService.add(
            { severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: gtin.numero.toString() }
          );
          this.idGtin = gtin.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Salvar Gtin.', detail: msg });
        });
    }
    else {
      this.gtinServices.update(this.gtin)
        .then((gtin: GtinModel) => {
          this.messageService.add(
            { severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: gtin.numero.toString() }
          );
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Gtin.', detail: msg });
        });
    }

  }

  clearGtin(form: NgForm): void {
    form.reset();
    this.gtin = new GtinModel();
    this.idGtin = undefined;
  }

  removeGtin(form: NgForm): void {
    this.gtinServices.delete(this.idGtin)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idGtin} não pode mais ser acessado` }
        );
        this.clearGtin(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Produto.', detail: msg });
      });
  }

  newBrick(): void {
    const ref = this.dialogService.open(BrickComponent, {
      width: '50%'
    });
    ref.onClose.subscribe(() => this.consult());
  }

  newClass(): void {
    const ref = this.dialogService.open(ClassComponent, {

      width: '50%'
    });
    ref.onClose.subscribe(() => this.consult());
  }

}
