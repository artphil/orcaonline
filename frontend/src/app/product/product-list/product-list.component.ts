import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SelectItem } from 'primeng/api/selectitem';

import { ProductFilterModel, ProductModel, SegmentModel, FamilyModel, ClassModel, BrickModel } from '../product.model';
import { ProductService } from '../product/product.service';
import { SegmentService } from '../segment/segment.service';
import { FamilyService } from '../family/family.service';
import { ClassService } from '../class/class.service';
import { BrickService } from '../brick/brick.service';

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
  productClasses: SelectItem[];
  productBricks: SelectItem[];

  constructor(
    private router: Router,
    private productService: ProductService,
    private segmentService: SegmentService,
    private familyService: FamilyService,
    private classService: ClassService,
    private brickService: BrickService
  ) { }

  ngOnInit(): void {

    this.segmentService.getList()
      .then((segmentList: SegmentModel[]) => {
        this.productSegments = [{ label: 'Todos', value: null }];
        segmentList.forEach(item => {
          this.productSegments.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.productSegments = [
          { label: 'Nenhum Segmento cadastrado.', value: null }
        ];
      });

    this.familyService.getList()
      .then((familyList: FamilyModel[]) => {
        this.productFamilies = [{ label: 'Todos', value: null }];
        familyList.forEach(item => {
          this.productFamilies.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.productFamilies = [
          { label: 'Nenhuma Família cadastrada.', value: null }
        ];
      });

    this.classService.getList()
      .then((classList: ClassModel[]) => {
        this.productClasses = [{ label: 'Todos', value: null }];
        classList.forEach(item => {
          this.productClasses.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.productClasses = [
          { label: 'Nenhuma Casse cadastrada.', value: null }
        ];
      });


    this.brickService.getList()
      .then((brickList: BrickModel[]) => {
        this.productBricks = [{ label: 'Todos', value: null }];
        brickList.forEach(item => {
          this.productBricks.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.productBricks = [
          { label: 'Nenhum Brick cadastrado.', value: null }
        ];
      });

    this.search();
  }

  search(query: any = {}): void {
    if (this.filter.id) {
      this.productService.getOne(this.filter.id)
        .then((product: ProductModel) => {
          this.products = [product];
        })
        .catch(() => []);
    } else {
      if (this.filter.nome) {
        query.nome = this.filter.nome;
      }

      this.productService.getList()
        .then((productList: ProductModel[]) => {
          this.products = productList;
        })
        .catch(() => []);
    }

  }
  searchBySegment(): void {
    if (this.filter.segmento.id) {


      const query = { segmento: { id: this.filter.segmento.id } };
      this.search(query);
    } else {
      this.search();
    }
  }

  searchByFamily(): void {
    if (this.filter.familia.id) {


      const query = { familia: { id: this.filter.familia.id } };
      this.search(query);
    } else {
      this.search();
    }
  }

  searchByClass(): void {
    if (this.filter.classe.id) {


      const query = { classe: { id: this.filter.classe.id } };
      this.search(query);
    } else {
      this.search();
    }
  }

  searchByBrick(): void {
    if (this.filter.brick.id) {


      const query = { brick: { id: this.filter.brick.id } };
      this.search(query);
    } else {
      this.search();
    }
  }

  editProduct(id: string): void {
    this.router.navigateByUrl('/pdt/' + id);
  }

}
