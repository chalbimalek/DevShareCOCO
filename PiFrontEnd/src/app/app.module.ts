import { NgModule,CUSTOM_ELEMENTS_SCHEMA,NO_ERRORS_SCHEMA } from '@angular/core';
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
import { FilterPipe } from './image/filter.pipe';
import {MatGridListModule} from '@angular/material/grid-list';
import { DragDirective } from './drag.directive';
import { ListProduitComponent } from './BackOffice/alltemplate-back/list-produit/list-produit.component';
import { ShowProductDetailsComponent } from './MarketPlacee/show-product-details/show-product-details.component';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import { ProdutShowDialogComponent } from './MarketPlacee/produt-show-dialog/produt-show-dialog.component';
import { AddProductBackComponent } from './MarketPlacee/add-product-back/add-product-back.component';
import { CommonModule } from '@angular/common';
import { DetaitlsbackComponent } from './MarketPlacee/detaitlsback/detaitlsback.component';
import { MatCardModule } from '@angular/material/card';
import { VaryingmodalcontentComponent } from './MarketPlacee/varyingmodalcontent/varyingmodalcontent.component';
import { AlltemplateBackComponent } from './BackOffice/alltemplate-back/alltemplate-back.component';
import { FooterBackComponent } from './BackOffice/footer-back/footer-back.component';
import { NavbarBackComponent } from './BackOffice/navbar-back/navbar-back.component';
import { SidebarBackComponent } from './BackOffice/sidebar-back/sidebar-back.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AnnonceCollocationComponent } from './annonce-collocation/annonce-collocation.component'; 
import { HeaderfrontComponent } from './headerfront/headerfront.component';
import { FooterfrontComponent } from './footerfront/footerfront.component';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { GeoapifyGeocoderAutocompleteModule } from '@geoapify/angular-geocoder-autocomplete';
import { AddCollocationComponent } from './add-collocation/add-collocation.component';
import { LoaderComponent } from './loader/loader.component';
import { EditAnnoanceComponent } from './edit-annoance/edit-annoance.component';
import { AnnoanceAdminComponent } from './BackOffice/annoance-admin/annoance-admin.component';
import { DetailsannoanceComponent } from './detailsannoance/detailsannoance.component';
import { MesRendezVousComponent } from './mes-rendez-vous/mes-rendez-vous.component';
import { FullCalendarModule } from '@fullcalendar/angular';

const firebaseConfig = {
  apiKey: "AIzaSyABT2TMHtazro4-Q-AK3Cj3d1ufUcTVBpk",
  authDomain: "coco-da825.firebaseapp.com",
  databaseURL: 'https://coco-da825-default-rtdb.firebaseio.com/',
  projectId: "coco-da825",
  storageBucket: "coco-da825.appspot.com",
  messagingSenderId: "1048397470509",
  appId: "1:1048397470509:web:394ca78e327b247079d1a4"
};
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MarketplaceComponent,
    RegisterProductComponent,
    HeaderfrontComponent,
    FooterfrontComponent,
    FilterPipe,
    DragDirective,
    ListProduitComponent,
    ShowProductDetailsComponent,
    ProdutShowDialogComponent,
    AddProductBackComponent,
    DetaitlsbackComponent,
    VaryingmodalcontentComponent,
    AlltemplateBackComponent,
    FooterBackComponent,
    NavbarBackComponent,
    SidebarBackComponent,
    AnnonceCollocationComponent,
    AddCollocationComponent,
    LoaderComponent,
    EditAnnoanceComponent,
    AnnoanceAdminComponent,
    DetailsannoanceComponent,
    MesRendezVousComponent,
    
  ],
   schemas: [ NO_ERRORS_SCHEMA,CUSTOM_ELEMENTS_SCHEMA ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatStepperModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    HttpClientModule,
    FullCalendarModule,
    FormsModule,
    GeoapifyGeocoderAutocompleteModule.withConfig('b04b64ac18e440efb505e01ab3012708'),
    AngularFireModule.initializeApp(firebaseConfig),
    AngularFireStorageModule,
    AngularFirestoreModule,
    MatGridListModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    CommonModule,
    MatCardModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
