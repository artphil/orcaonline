import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

import { ProductModel } from '../product.model';
import { ProductService } from '../product.service';

@Component({
	selector: 'app-product-list',
	templateUrl: './product-list.component.html',
	styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

	products: ProductModel[];
	filter = new ProductModel();
	productSegments: SelectItem[];
	productFamilies: SelectItem[];
	productClasses: SelectItem[];

	constructor(
		private router: Router,
		private productService: ProductService) { }

	ngOnInit(): void {

		this.productSegments = [
			{ label: 'Todos', value: '' }
		];

		this.productFamilies = [
			{ label: 'Todos', value: '' }
		];

		this.productClasses = [
			{ label: 'Todos', value: '' }
		];

		this.productService.getList()
			.then((productList: ProductModel[]) => {
				this.products = productList;
			})
			.catch(() => { return [] });
	}

	editProduct(id:string) {
	this.router.navigateByUrl('/pdt/'+id)
	}

}
