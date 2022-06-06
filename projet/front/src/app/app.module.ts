import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {TableModule} from "primeng/table";
import {ProgressBarModule} from "primeng/progressbar";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {MultiSelectModule} from "primeng/multiselect";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    TableModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ProgressBarModule,
    InputTextModule,
    CalendarModule,
    DropdownModule,
    MultiSelectModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent
  ]
})
export class AppModule { }
