import { Component, Input } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { PedidoService } from '../pedido.service';
import { PedVndCardDto, PedVndProdCardDto, STATUS_PEDIDO } from './pedido-card.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'cmp-pedido-card',
  templateUrl: './pedido-card.component.html',
  styleUrl: './pedido-card.component.scss'
})
export class PedidoCardComponent {

  @Input() public codPedVnd: number;


  protected pedVndCardDto: PedVndCardDto;
  protected prodList: PedVndProdCardDto[];
  protected status: any = STATUS_PEDIDO;

  constructor(
    private sanitizer: DomSanitizer, 
    private service: PedidoService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  public ngOnInit(): void {
    this.service.getPedVndCardDto(this.codPedVnd).subscribe(pedVndCardDto => {
      this.pedVndCardDto = pedVndCardDto;
    });
    this.service.getPedVndProdCardDtoList(this.codPedVnd).subscribe(prodList => {
      this.prodList = prodList;
    });
  }

  protected getSrc(img: any): SafeUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/png;base64,' + img);
  }

  protected detalhe(): void {
    this.router.navigate([this.codPedVnd], { relativeTo: this.activatedRoute })
  }
}
