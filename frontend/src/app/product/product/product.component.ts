import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';
import { MessageService } from 'primeng/api';

import { ProductModel, GtinModel, NcmModel } from '../product.model';
import { ProductService } from './product.service';
import { GtinService } from '../gtin/gtin.service';
import { DialogService } from 'primeng/dynamicdialog';
import { GtinComponent } from '../gtin/gtin.component';
import { NcmService } from '../ncm/ncm.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: ProductModel;
  productNCMs: SelectItem[];
  productGTINs: SelectItem[];

  isNewProduct: boolean;
  idProduct: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService,
    private ncmService: NcmService,
    private gtinService: GtinService,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.idProduct = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.ncmService.getList()
    .then((ncmList: NcmModel[]) => {
      this.productNCMs = [];
      ncmList.forEach( item => {
        this.productNCMs.push({ label: item.numero.toString(), value: item.id });
      });
    })
    .catch(() => {
      this.productNCMs = [
        { label: 'Nenhum NCM cadastrado', value: {} }
      ];
    });

    this.gtinService.getList()
      .then((gtinList: GtinModel[]) => {
        this.productGTINs = [];
        gtinList.forEach( item => {
          this.productGTINs.push({ label: item.numero.toString(), value: item.id });
        });
      })
      .catch(() => {
        this.productGTINs = [
          { label: 'Nenhum GTIN cadastrado', value: {} }
        ];
      });

  }

  consult(): void {
    this.isNewProduct = !this.idProduct;

    if (this.isNewProduct) {
      this.product = new ProductModel();
    } else {
      this.productService.getOne(this.idProduct)
        .then((product: ProductModel) => {
          this.product = product ? product : new ProductModel();
        })
        .catch(() => {
          this.product = new ProductModel();
        });
    }
  }

  saveProduct(form: NgForm): void {
    if (this.isNewProduct) {
      this.productService.create(this.product)
        .then((product: ProductModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: product.nome });
          this.router.navigateByUrl(`/pdt/${product.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));
    }
    else {
      this.productService.update(this.product)
        .then((product: ProductModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: product.nome });
          this.consult();
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearProduct(form: NgForm): void {
    form.reset();
    this.product = new ProductModel();
    this.router.navigateByUrl('/pdt');
  }

  removeProduct(form: NgForm): void {
    this.productService.delete(this.idProduct)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idProduct} não pode mais ser acessado` }
          );
        this.router.navigateByUrl('/pdt');
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
        )
      );
  }

  showDialog(): void {
    const ref = this.dialogService.open(GtinComponent, {
      width: '50%'
    });
  }
}
