import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Technical } from 'src/app/models/technician';

@Component({
  selector: 'app-techinician-list',
  templateUrl: './techinician-list.component.html',
  styleUrls: ['./techinician-list.component.css']
})
export class TechinicianListComponent implements OnInit {

  ELEMENT_DATA: Technical[] = [
    {
      id: 1,
      name: 'Leonardo dos Santos Miranda',
      cpf: '123.456.789-10',
      email: 'leonardo@gmail.com',
      password: '1234',
      profile: ['0'],
      dateCreation: '22/05/2024'
    }
  ]

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'action'];
  dataSource = new MatTableDataSource<Technical>(this.ELEMENT_DATA);

  constructor() { }

  ngOnInit(): void {
  }


  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
