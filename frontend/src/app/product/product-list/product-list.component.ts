import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

import { ProductFilterModel, ProductModel, SegmentModel, FamilyModel } from '../product.model';
import { ProductService } from '../product/product.service';
import { SegmentService } from '../segment/segment.service';
import { FamilyService } from '../family/family.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: ProductModel[];
  filter = new ProductFilterModel();
  productSegments: SelectItem[];
  productFamilies: SelectItem[];
  productBricks: SelectItem[];
  productClasses: SelectItem[];

  constructor(
    private router: Router,
    private productService: ProductService,
    private segmentService: SegmentService,
    private familyService: FamilyService
  ) { }

  ngOnInit(): void {

    this.segmentService.getList()
      .then((segmentList: SegmentModel[]) => {
        this.productSegments = [{ label: 'Todos', value: {} }];
        segmentList.forEach(s => {
          this.productSegments.push({ label: s.nome, value: { id: s.id } })
        });
      })
      .catch(() => {
        this.productSegments = [
          { label: 'Nenhum Segmento cadastrado.', value: {} }
        ];
      });

    this.familyService.getList()
      .then((familyList: FamilyModel[]) => {
        this.productFamilies = [{ label: 'Todos', value: {} }];
        familyList.forEach( f => {
          this.productFamilies.push({ label: f.nome, value: { id: f.id } });
        });
      })
      .catch(() => {
        this.productFamilies = [
          { label: 'Nenhuma Família cadastrada.', value: {} }
        ];
      });
    this.productFamilies = [
      { label: 'Todos', value: '' }
    ];

    this.productClasses = [
      { label: 'Todos', value: '' }
    ];

    this.productBricks = [
      { label: 'Todos', value: '' }
    ];

    this.productService.getList()
      .then((productList: ProductModel[]) => {
        this.products = productList;
      })
      .catch(() => { return [] });
  }

  editProduct(id: string): void {
    this.router.navigateByUrl('/pdt/' + id);
  }

}
