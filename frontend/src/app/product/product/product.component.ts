import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

import { ProductModel, GtinModel } from '../product.model';
import { ProductService } from './product.service';
import { GtinService } from '../gtin/gtin.service';

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
    private gtinService: GtinService
  ) { }

  ngOnInit(): void {
    this.idProduct = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.productNCMs = [
      { label: 'Segmento 1', value: 'Teste1' },
      { label: 'Segmento 2', value: 'Teste2' }
    ];

    this.gtinService.getList()
      .then((segmentList: GtinModel[]) => {
        this.productGTINs = [{ label: 'Todos', value: {} }];
        segmentList.forEach(s => {
          this.productGTINs.push({ label: s.numero, value: { 'id': s.id } })
        });
      })
      .catch(() => {
        this.productGTINs = [
          { label: 'Todos', value: {} }
        ];
      });


  }

  consult(): void {
    this.isNewProduct = !this.idProduct;
    console.log('product:', this.idProduct);

    if (this.isNewProduct) {
      console.log('new')
      this.product = new ProductModel();
    } else {
      this.productService.getOne(this.idProduct)
        .then((product: ProductModel) => {
          console.log(product ? product : 'nada');
          this.product = product ? product : new ProductModel();
        })
        .catch(() => {
          this.product = new ProductModel();
          console.log('catch', this.product);
        });
    }
  }

  saveProduct(form: NgForm): void {
    if (this.isNewProduct) {
      this.productService.create(this.product)
        .then((product: ProductModel) => {
          this.router.navigateByUrl(`/pdt/${product.id}`)
        })
        .catch(() => alert('Solicitação não concluida.'));;
    }
    else {
      this.productService.update(this.product)
        .then((product: ProductModel) => {
          this.consult()
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearProduct(form: NgForm): void {
    form.reset();
    this.product = new ProductModel();
    this.router.navigateByUrl('/pdt')
  }

  removeProduct(form: NgForm): void {
    this.productService.delete(this.idProduct)
      .then(() => {
        alert("Produto Exluido");
        this.router.navigateByUrl('/pdt')
      })
      .catch(() => alert('Solicitação não concluida.'));
  }
}
