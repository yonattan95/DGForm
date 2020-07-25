import { Test, TestingModule } from '@nestjs/testing';
import { FormController } from './form.controller';
import { FormService } from './form.service';
import { FormRequest } from './data/dto/form.dto';
import { Form } from './data/entities/form.entity';

describe('Modulo Gestion de Formularios', () => {
  let formController: FormController;

  beforeEach(async () => {
    const form: TestingModule = await Test.createTestingModule({
      controllers: [FormController],
      providers: [FormService],
    }).compile();

    formController = form.get<FormController>(FormController);
  });

  describe('Listar formularios pendientes', () => {
    it('Obtiene una lista de formularios pendientes', () => {
      expect(formController.getPendingFormList()).toEqual([]);
    });
  });
  describe('Listar formularios completados', () => {
    it('Obtiene una lista de formularios completados', () => {
      expect(formController.getCompleteFormList()).toEqual([]);
    });
  });
  describe('Obtener formulario por ID', () => {
    it('Obtiene un formularios con el ID especificado', () => {
      expect(formController.getFormById(2)).toEqual([]);
    });
  });
  describe('Crear formulario ', () => {
    it('Se genera un nuevo formulario', () => {
      expect(formController.newForm(new Form())).toEqual([]);
    });
  });
});
