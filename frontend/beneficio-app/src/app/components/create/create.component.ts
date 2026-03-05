import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Beneficio } from 'src/app/Models/Beneficio.model';
import { BeneficioService } from 'src/app/services/beneficio.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent  {
   
  novoBeneficio: Beneficio = {
    nome: '',
    descricao: '',
    valor: 0,
    ativo: true
  };

  mensagem: string = '';
  tipoMensagem: 'sucesso' | 'erro' = 'erro';
  processando: boolean = false;

  constructor(private service: BeneficioService, private router: Router) { }

  salvar() {
    if (!this.novoBeneficio.nome || !this.novoBeneficio.descricao) {
      this.exibirMensagem('Por favor, preencha o nome e a descrição.', 'erro');
      return;
    }

    this.processando = true;
    this.service.criarBeneficio(this.novoBeneficio).subscribe({
      next: () => {
        this.exibirMensagem('Benefício cadastrado com sucesso!', 'sucesso');
        setTimeout(() => this.router.navigate(['/beneficios']), 1500);
      },
      error: (err) => {
        this.exibirMensagem('Erro ao cadastrar: ' + (err.error?.message || 'Erro interno'), 'erro');
        this.processando = false;
      }
    });
  }

  private exibirMensagem(texto: string, tipo: 'sucesso' | 'erro') {
    this.mensagem = texto;
    this.tipoMensagem = tipo;
  }

}
