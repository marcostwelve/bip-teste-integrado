import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BeneficioService } from 'src/app/services/beneficio.service';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css']
})
export class TransferenciaComponent {

  idOrigem: number = 0;
  idDestino: number = 0;
  valor: number = 0;

  mensagem: string = '';
  tipoMensagem: 'sucesso' | 'erro' = 'erro';
  processando: boolean = false;

  constructor(private beneficioService: BeneficioService, private router: Router) { }

  executarTransferencia() {
    if (!this.idOrigem || !this.idDestino || !this.valor) {
      this.exibirMensagem('Por favor, preencha todos os campos.', 'erro');
      return;
    }

    if (this.idOrigem === this.idDestino) {
      this.exibirMensagem('O ID de origem não pode ser igual ao de destino.', 'erro');
      return;
    }

    this.processando = true;
    this.mensagem = '';

    this.beneficioService.traferirBeneficio(this.idOrigem, this.idDestino, this.valor).subscribe({
      next: (res) => {
        this.exibirMensagem('Transferência realizada com sucesso!', 'sucesso');
        this.processando = false;
        setTimeout(() => this.router.navigate(['/beneficios']), 1500);
      },
      error: (err) => {
        const erroMsg = err.error?.message || (typeof err.error === 'string' ? err.error : 'Erro ao processar transferência.');
        this.exibirMensagem(erroMsg, 'erro');
        this.processando = false;
      }
    });
  }

  private exibirMensagem(texto: string, tipo: 'sucesso' | 'erro') {
    this.mensagem = texto;
    this.tipoMensagem = tipo;
  }
}
