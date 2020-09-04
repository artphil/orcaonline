import { Component, OnInit } from '@angular/core';
import { GtinModel, BrickModel } from '../product.model';
import { SelectItem, MessageService } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { GtinService } from './gtin.service';
import { BrickService } from '../brick/brick.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-gtin',
  templateUrl: './gtin.component.html',
  styleUrls: ['./gtin.component.css']
})
export class GtinComponent implements OnInit {

  gtin: GtinModel;
  gtinBricks: SelectItem[];

  isNewGtin: boolean;
  idGtin: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private gtinServices: GtinService,
    private brickServices: BrickService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.idGtin = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.brickServices.getList()
      .then((brickList: BrickModel[]) => {
        this.gtinBricks = [];
        brickList.forEach( item => {
          this.gtinBricks.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.gtinBricks = [
          { label: 'Nenhum Brick cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    this.isNewGtin = !this.idGtin;

    if (this.isNewGtin) {
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
    if (this.isNewGtin) {
      this.gtinServices.create(this.gtin)
        .then((gtin: GtinModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: gtin.numero.toString() });
          this.router.navigateByUrl(`/pdt/gtn/${gtin.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));
    }
    else {
      this.gtinServices.update(this.gtin)
        .then((gtin: GtinModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: gtin.numero.toString() });
          this.consult();
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearGtin(form: NgForm): void {
    form.reset();
    this.gtin = new GtinModel();
    this.router.navigateByUrl('/pdt/gtn');
  }

  removeGtin(form: NgForm): void {
    this.gtinServices.delete(this.idGtin)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idGtin} não pode mais ser acessado` }
        );
        this.router.navigateByUrl('/pdt/gtn');
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
      )
      );
  }

  newBrick(): void {
    this.router.navigateByUrl('/pdt/brk');
  }


}
