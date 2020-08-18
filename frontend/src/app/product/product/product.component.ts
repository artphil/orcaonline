import { ProductService } from './../product.service';
import { ProductModel } from '../product.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: ProductModel = new ProductModel;

  constructor(private service: ProductService) { }

  ngOnInit(): void {
    console.log(this.product);
  }

  saveProduct(): void {

  }
}
