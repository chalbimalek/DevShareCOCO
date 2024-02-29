import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MarketplaceComponent } from './MarketPlacee/marketplace/marketplace.component';
import { RegisterProductComponent } from './MarketPlacee/register-product/register-product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material/stepper';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { FilterPipe } from './filter.pipe';
import { AddCarpoolingComponent } from './carpooling/add-carpooling/add-carpooling.component';
import { CarpoolingListComponent } from './carpooling/carpooling-list/carpooling-list.component';
import { CarpoolingDetailsComponent } from './carpooling/carpooling-details/carpooling-details.component';
import { ReactiveComponent } from './carpooling/reactive/reactive.component';







@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MarketplaceComponent,
    RegisterProductComponent,
    NavbarComponent,
    FooterComponent,
    FilterPipe,
    AddCarpoolingComponent,
    CarpoolingListComponent,
    CarpoolingDetailsComponent,
    ReactiveComponent,
    
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatStepperModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    HttpClientModule,
    FormsModule,
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
