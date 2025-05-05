import { DatePipe } from "@angular/common";
import { HttpParams } from "@angular/common/http";
import { DATE_FORMAT } from "../../../environments/environment";

export class HttpParamsObject extends HttpParams {

    constructor(object: any, dateFormat: string = DATE_FORMAT) {
        let datePipe = new DatePipe("pt");
        let objectParams: any = {};
        for (let key in object) {
            let value = object[key];
            if (value instanceof Date) {
                value = datePipe.transform(value, dateFormat);
            }
            objectParams[key] = value;
        }
        super({ fromObject: objectParams });
    }
}