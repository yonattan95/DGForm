import {
  Controller,
  Get,
  Post,
  Req,
  Body,
  Param,
} from '@nestjs/common';
import { FormService } from './form.service';
import {
  ResponseAPI,
  SuccessResponse,
} from '../common/dto/response.dto';
import { FormResponse, FormRequest } from './data/dto/form.dto';
import { Request } from 'express';
import { Form } from './data/entities/form.entity';

@Controller('forms')
export class FormController {
  constructor(private readonly formService: FormService) {}

  @Get('pending')
  async getPendingFormList(): Promise<ResponseAPI<Array<Form>>> {
    const list = await this.formService.getPendingFormList();
    return new SuccessResponse(list);
  }
  @Get('complete')
  async getCompleteFormList(): Promise<ResponseAPI<Array<Form>>> {
    const list = await this.formService.getCompleteFormList();
    return new SuccessResponse(list);
  }
  @Get(':id')
  async getFormById(@Param('id') id: number): Promise<ResponseAPI<Form>> {
    const form = await this.formService.getFormById(id);
    return new SuccessResponse(form);
  }
  @Post()
  async newForm(@Body() body: Form): Promise<ResponseAPI<Form>> {
    await this.formService.saveForm(body);
    return new SuccessResponse(body);
  }
}
