import { FormControl, FormGroup } from "@angular/forms";

type Controls<T> = {
    [K in keyof T]: T[K] extends Date
    ? FormControl<T[K]> 
    : T[K] extends object ? FormGroupModel<T[K]>
    : FormControl<T[K]>;
};

export class FormGroupModel<T> extends FormGroup<Controls<T>> {

    constructor(instance: T) {
        let controls = {} as any;
        for (let key of Object.keys(instance) as (keyof T)[]) {
            if (instance[key] != null) {
                controls[key] = new FormGroupModel(instance[key]);
            } else {
                controls[key] = new FormControl(instance[key]);
            }
        }
        super(controls);
    }
}


