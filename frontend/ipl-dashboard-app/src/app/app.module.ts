import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { StoreModule } from '@ngrx/store';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { TeamdetailsComponent } from './teamdetails/teamdetails.component';
import { MatchdetailsComponent } from './matchdetails/matchdetails.component';
import { TeamtileComponent } from './home/teamtile/teamtile.component';

import * as fromApp from './store/app.reducer';
import {EffectsModule} from "@ngrx/effects";
import {TeamsEffects} from "./home/store/teams.effects";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TeamdetailsComponent,
    MatchdetailsComponent,
    TeamtileComponent
  ],
  imports: [
    StoreModule.forRoot(fromApp.appReducer),
    EffectsModule.forRoot([
      TeamsEffects
    ]),
    HttpClientModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
