import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Form } from './data/entities/form.entity';
import { Repository } from 'typeorm';
import { FormResponse, FormRequest } from './data/dto/form.dto';
import NewFormI from './data/interfaces/form.interface';

@Injectable()
export class FormService {
  constructor(
    @InjectRepository(Form) private formRepository: Repository<Form>,
  ) {}
  async saveForm(form: NewFormI): Promise<boolean> {
    const res = await this.formRepository.save(form);
    return res ? true : false;
  }
  async getPendingFormList(): Promise<Array<Form>> {
    return this.formRepository.find({
      state: 0,
    });
  }
  async getCompleteFormList(): Promise<Array<Form>> {
    return this.formRepository.find({
      state: 1,
    });
  }
  async getFormById(id: number): Promise<Form> {
    return this.formRepository.findOne({
      id,
    });
  }

  async getAllForm() {
    return this.formRepository.find();
  }
}
