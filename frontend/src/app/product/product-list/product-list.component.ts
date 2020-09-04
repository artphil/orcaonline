import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

import { ProductFilterModel, ProductModel, SegmentModel, FamilyModel, ClassModel } from '../product.model';
import { ProductService } from '../product/product.service';
import { SegmentService } from '../segment/segment.service';
import { FamilyService } from '../family/family.service';
import { ClassService } from '../class/class.service';

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
    private familyService: FamilyService,
    private classService: ClassService
  ) { }

  ngOnInit(): void {

    this.segmentService.getList()
      .then((segmentList: SegmentModel[]) => {
        this.productSegments = [{ label: 'Todos', value: {} }];
        segmentList.forEach(item => {
          this.productSegments.push({ label: item.nome, value: { id: item.id } })
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
        familyList.forEach(item => {
          this.productFamilies.push({ label: item.nome, value: { id: item.id } });
        });
      })
      .catch(() => {
        this.productFamilies = [
          { label: 'Nenhuma Família cadastrada.', value: {} }
        ];
      });

    this.classService.getList()
      .then((classList: ClassModel[]) => {
        this.productClasses = [{ label: 'Todos', value: {} }];
        classList.forEach(item => {
          this.productClasses.push({ label: item.nome, value: { id: item.id } });
        });
      })
      .catch(() => {
        this.productClasses = [
          { label: 'Nenhuma Casse cadastrada.', value: {} }
        ];
      });

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
