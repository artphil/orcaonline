import { Component, OnInit } from '@angular/core';
import { BrickModel, ClassModel } from '../product.model';
import { SelectItem, MessageService } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { BrickService } from './brick.service';
import { ClassService } from '../class/class.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-brick',
  templateUrl: './brick.component.html',
  styleUrls: ['./brick.component.css']
})
export class BrickComponent implements OnInit {

  brick: BrickModel;
  brickClasses: SelectItem[];

  isNewBrick: boolean;
  idBrick: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private brickServices: BrickService,
    private classServices: ClassService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.idBrick = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.classServices.getList()
      .then((classList: ClassModel[]) => {
        this.brickClasses = [];
        classList.forEach( item => {
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
    this.isNewBrick = !this.idBrick;

    if (this.isNewBrick) {
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
    if (this.isNewBrick) {
      this.brickServices.create(this.brick)
        .then((brick: BrickModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: brick.nome });
          this.router.navigateByUrl(`/pdt/brk/${brick.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));
    }
    else {
      this.brickServices.update(this.brick)
        .then((brick: BrickModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: brick.nome });
          this.consult();
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearBrick(form: NgForm): void {
    form.reset();
    this.brick = new BrickModel();
    this.router.navigateByUrl('/pdt/brk');
  }

  removeBrick(form: NgForm): void {
    this.brickServices.delete(this.idBrick)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idBrick} não pode mais ser acessado` }
        );
        this.router.navigateByUrl('/pdt/brk');
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
      )
      );
  }

  newClass(): void {
    this.router.navigateByUrl('/pdt/cls/');
  }


}
