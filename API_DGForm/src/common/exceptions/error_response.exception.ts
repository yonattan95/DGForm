import { HttpException, HttpStatus } from '@nestjs/common';

export default class ErrorResponseException extends HttpException {
  constructor({ errorMessage }) {
    super(
      {
        status: 0,
        time: new Date(),
        data: null,
        errorMessage,
      },
      HttpStatus.OK,
    );
  }
}
