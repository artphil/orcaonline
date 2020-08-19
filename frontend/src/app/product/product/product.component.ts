import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/api';

import { ProductModel } from '../product.model';
import { ProductService } from './../product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: ProductModel;
  productSegments: SelectItem[];
  productFamily: SelectItem[];
  productClass: SelectItem[];

  isNewProduct = false;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productSegments = [
      { label: 'Segmento 1', value: 1 },
      { label: 'Segmento 2', value: 2 }
    ];

    this.productFamily = [
      { label: 'Familia 1', value: 1 },
      { label: 'Familia 2', value: 2 }
    ];

    this.productClass = [
      { label: 'Classe 1', value: 1 },
      { label: 'Classe 2', value: 2 }
    ];

    if (this.isNewProduct) {
      this.product = new ProductModel();
    } else {
      this.productService.getOne()
        .then((product: ProductModel) => {
          this.product = product;
        })
        .catch(() => {
          this.product = new ProductModel();
        });
    }
  }

  saveProduct(form: NgForm): void {
    console.log(this.product);
    form.reset();
  }

  newProduct(form: NgForm): void {
    this.product = new ProductModel();
    form.reset();
  }

  removeProduct(form: NgForm): void {
    console.log(form);
    // form.reset();
  }
}
