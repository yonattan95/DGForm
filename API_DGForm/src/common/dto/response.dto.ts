import { throws } from 'assert';

enum statusEnum {
  success = 1,
  fail = 0,
}
export abstract class ResponseAPI<T> {
  private status: statusEnum;
  private time: Date;
  private data: T | null;
  private errorMessage: string | null;
  constructor(status: statusEnum, data: T, errorMessage = null) {
    this.status = status;
    this.time = new Date();
    this.data = data;
    this.errorMessage = errorMessage;
  }
}

export class SuccessResponse<T> extends ResponseAPI<T> {
  constructor(data: T) {
    super(statusEnum.success, data);
  }
}
export class FailResponse<T> extends ResponseAPI<T> {
  constructor(errorMessage: string) {
    super(statusEnum.fail, null, errorMessage);
  }
}
