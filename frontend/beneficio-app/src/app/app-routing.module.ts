import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BeneficioListComponent } from './components/beneficio-list/beneficio-list.component';
import { TransferenciaComponent } from './components/transferencia/transferencia.component';
import { CreateComponent } from './components/create/create.component';

const routes: Routes = [
  { path: 'beneficios', component: BeneficioListComponent },
  { path: 'transferir', component: TransferenciaComponent },
  {path: 'criar', component: CreateComponent},
  { path: '', redirectTo: '/beneficios', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
