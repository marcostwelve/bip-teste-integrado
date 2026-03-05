import { Component, OnInit } from '@angular/core';
import { Beneficio } from 'src/app/Models/Beneficio.model';
import { BeneficioService } from 'src/app/services/beneficio.service';

@Component({
  selector: 'app-beneficio-list',
  templateUrl: './beneficio-list.component.html',
  styleUrls: ['./beneficio-list.component.css']
})
export class BeneficioListComponent implements OnInit {
  beneficios: Beneficio[] = [];

  constructor(private beneficioService: BeneficioService) { }

  ngOnInit(): void {
    this.carregarBeneficios();
  }

  carregarBeneficios(): void {
    this.beneficioService.listarBeneficios().subscribe( dados => {
      this.beneficios = dados;
    });
  }

}
