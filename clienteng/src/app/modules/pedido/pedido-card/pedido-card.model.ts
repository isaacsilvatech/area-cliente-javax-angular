export interface PedVndCardDto {
    codPedVnd: number;
    dataCadastro: Date;
    status: number;
    descricao: string;
    valorTotalBruto: number;
    valorTotalDesconto: number;
}

export interface PedVndProdCardDto {
    codProd: string;
    imagem: any;
}

export const STATUS_PEDIDO: any = {
    '1': {
        label: 'A PAGAR',
        bg: 'var(--red-200)',
        color: 'var(--red-800)',
        icon: 'pi pi-dollar'
    },
    '2': {
        label: 'A PAGAR',
        bg: 'var(--red-200)',
        color: 'var(--red-800)',
        icon: 'pi pi-dollar'
    },
    '3': {
        label: 'PAGO',
        bg: 'var(--green-200)',
        color: 'var(--green-800)',
        icon: 'pi pi-dollar'
    },
    '4': {
        label: 'DESPACHADO',
        bg: 'var(--indigo-200)',
        color: 'var(--indigo-800)',
        icon: 'pi pi-truck'
    },
    '5': {
        label: 'ENTREGUE',
        bg: 'var(--purple-200)',
        color: 'var(--purple-800)',
        icon: 'pi pi-box'
    },
    '6': {
        label: 'FINALIZADO',
        bg: 'var(--cyan-200)',
        color: 'var(--cyan-800)',
        icon: 'pi pi-check'
    },
    '7': {
        label: 'CANCELADO',
        bg: 'var(--bluegray-200)',
        color: 'var(--bluegray-800)',
        icon: 'pi pi-times'
    },
    '8': {
        label: 'EXPIRADO',
        bg: 'var(--orange-200)',
        color: 'var(--orange-800)',
        icon: 'pi pi-times-circle'
    },
};