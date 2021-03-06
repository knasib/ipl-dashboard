import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {TeamdetailsComponent} from "./teamdetails/teamdetails.component";
import {MatchdetailsComponent} from "./matchdetails/matchdetails.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'teams/:teamname/:year', component: TeamdetailsComponent},
  {path: 'matchdetails/:matchid', component: MatchdetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
