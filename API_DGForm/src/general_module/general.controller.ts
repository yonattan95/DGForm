import { Controller, Get } from '@nestjs/common';
import { SuccessResponse } from 'src/common/dto/response.dto';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
import CategoryService from './category.service';
import QuestionTypeService from './option_type.service';

@Controller('generals')
export default class GeneralController {
  constructor(
    private readonly categoryService: CategoryService,
    private readonly questionTypeService: QuestionTypeService,
  ) {}

  @Get('categories')
  async getCategoryList() {
    const res = await this.categoryService.getAll();
    if (!res)
      throw new ErrorResponseException({
        errorMessage:
          'Hubo un error al cargar la lista de categorias para los formularios',
      });
    return new SuccessResponse(res);
  }
  @Get('question_types')
  async getQuestionTypeList() {
    const res = await this.questionTypeService.getAll();
    if (!res)
      throw new ErrorResponseException({
        errorMessage:
          'Hubo un error al cargar la lista de Tipos de pregunta para los formularios',
      });
    return new SuccessResponse(res);
  }
}
