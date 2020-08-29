import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

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
  productFamilies: SelectItem[];
  productClasses: SelectItem[];

  isNewProduct: boolean;
  idProduct: number;

  constructor( 
	  private route: ActivatedRoute,
	  private router: Router,
	  private productService: ProductService
	  ) { }

  ngOnInit(): void {
	  console.log('ooo', this.route.snapshot.paramMap.get('cod'));
	  this.idProduct = Number(this.route.snapshot.paramMap.get('cod'));
			
	this.isNewProduct = !this.idProduct;
	console.log('aaa',this.idProduct);

    this.productSegments = [
      { label: 'Segmento 1', value: 'Teste1' },
      { label: 'Segmento 2', value: 'Teste2' }
    ];

    this.productFamilies = [
      { label: 'Familia 1', value: 'Teste1' },
      { label: 'Familia 2', value: 'Teste2' }
    ];

    this.productClasses = [
      { label: 'Classe 1', value: 'Teste1' },
      { label: 'Classe 2', value: 'Teste2' }
    ];

    if (this.isNewProduct) {
      this.product = new ProductModel();
    } else {
      this.productService.getOne(this.idProduct)
        .then((product: ProductModel) => {
			console.log(product? product : 'nada');
          this.product = product? product : new ProductModel();
        })
        .catch(() => {
			console.log('cath');
          this.product = new ProductModel();
        });
    }
  }

  saveProduct(form: NgForm): void {
	  if (this.isNewProduct) {
		  this.productService.create(this.product)
		  .then((product: ProductModel) => {
			  this.product = product;
			});
		}
		else {
			this.productService.update(this.idProduct, this.product)
			.then((product: ProductModel) => {
				this.product = product;
			  });
		}
	}

  clearProduct(form: NgForm): void {
	this.router.navigateByUrl('/pdt')
  }

  removeProduct(form: NgForm): void {
    console.log(form);
    // form.reset();
  }
}
