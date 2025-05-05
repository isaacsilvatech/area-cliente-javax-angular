import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card.component';
import { CardHeaderComponent } from './card-header/card-header.component';
import { CardContentComponent } from './card-content/card-content.component';



@NgModule({
  exports: [
    CardComponent,
    CardHeaderComponent,
    CardContentComponent
  ],
  declarations: [
    CardComponent,
    CardHeaderComponent,
    CardContentComponent
  ],
  imports: [
    CommonModule
  ]
})
export class CardModule { }
