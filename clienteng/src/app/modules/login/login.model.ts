export interface LoginCheck {
    login: string;
    senha: string;
}

export interface LoginToken {
    msg: string;
    token: string;
}

export interface OpcaoRecuperaSenha {
    tipo: 'email' | 'sms';
    descricao: string;
}