import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

import { ProductModel } from '../product.model';
import { ProductService } from './product.service';

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
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    this.idProduct = Number(this.route.snapshot.paramMap.get('cod'));

    this.isNewProduct = !this.idProduct;
    console.log('product:', this.idProduct);

    this.consult();

    this.productNCMs = [
      { label: 'Segmento 1', value: 'Teste1' },
      { label: 'Segmento 2', value: 'Teste2' }
    ];

    this.productGTINs = [
      { label: 'Familia 1', value: 'Teste1' },
      { label: 'Familia 2', value: 'Teste2' }
    ];

  }

  consult(): void {
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
          form.reset();

          console.log(`/pdt/${product.id}`)
          this.router.navigateByUrl(`/pdt/${product.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));;
    }
    else {
      this.productService.update(this.product)
        .then((product: ProductModel) => {
          form.reset();
          this.router.navigateByUrl(`/pdt/${product.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearProduct(form: NgForm): void {
    form.reset();
    this.router.navigateByUrl('/pdt')
  }

  removeProduct(form: NgForm): void {
    this.productService.delete(this.idProduct)
    .then(() => alert("Produto Exluido"))
    .catch(() => alert('Solicitação não concluida.'));
  }
}
