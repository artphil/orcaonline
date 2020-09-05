import { Component, OnInit } from '@angular/core';
import { BrickModel, ClassModel } from '../product.model';
import { SelectItem, MessageService } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { BrickService } from './brick.service';
import { ClassService } from '../class/class.service';
import { NgForm } from '@angular/forms';
import { DialogService } from 'primeng/dynamicdialog';
import { ClassComponent } from '../class/class.component';

@Component({
  selector: 'app-brick',
  templateUrl: './brick.component.html',
  styleUrls: ['./brick.component.css']
})
export class BrickComponent implements OnInit {

  brick: BrickModel;
  brickClasses: SelectItem[];

  idBrick: number;

  constructor(
    private route: ActivatedRoute,
    private brickServices: BrickService,
    private classServices: ClassService,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.idBrick = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.classServices.getList()
      .then((classList: ClassModel[]) => {
        this.brickClasses = [];
        classList.forEach(item => {
          this.brickClasses.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.brickClasses = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    if (!this.idBrick) {
      this.brick = new BrickModel();
    } else {
      this.brickServices.getOne(this.idBrick)
        .then((brick: BrickModel) => {
          this.brick = brick ? brick : new BrickModel();
        })
        .catch(() => {
          this.brick = new BrickModel();
        });
    }
  }

  saveBrick(form: NgForm): void {
    if (!this.idBrick) {
      this.brickServices.create(this.brick)
        .then((brick: BrickModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: brick.nome });
          this.idBrick = brick.id;
          this.consult();
        })
        .catch(() => this.messageService.add(
          { severity: 'error', summary: 'Falha ao Salvar Brick.', detail: 'Confira os campos e tente novamente' }
        ));
    }
    else {
      this.brickServices.update(this.brick)
        .then((brick: BrickModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: brick.nome });
          this.consult();
        })
        .catch(() => this.messageService.add(
          { severity: 'error', summary: 'Falha ao Alterar Brick.', detail: 'Confira os campos e tente novamente' }
        ));
    }

  }

  clearBrick(form: NgForm): void {
    form.reset();
    this.brick = new BrickModel();
    this.idBrick = undefined;
  }

  removeBrick(form: NgForm): void {
    this.brickServices.delete(this.idBrick)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idBrick} não pode mais ser acessado` }
        );
        this.clearBrick(form);
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
      )
      );
  }

  newClass(): void {
    const ref = this.dialogService.open(ClassComponent, {
      width: '50%'
    });
  }

}
