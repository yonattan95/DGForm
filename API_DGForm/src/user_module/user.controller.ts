import {
  Body,
  Controller,
  Get,
  Param,
  Post,
  Put,
  Delete,
  UseGuards,
} from '@nestjs/common';
import {
  FailResponse,
  ResponseAPI,
  SuccessResponse,
} from 'src/common/dto/response.dto';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
import { UserProfileResponse } from '../common/dto/profile.dto';
import {
  NewUserI,
  UpdateUserI,
} from './data/interfaces/user.interface';
import UserService from './user.service';
import JwtAuthGuard from 'src/auth_user_module/guards/jwt_auth.guard';
import { hashString } from 'src/common/helpers/security.helper';

@Controller('users')
export default class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  async newUser(@Body() user: NewUserI) {
    const passHas = hashString(user.password);
    const res = this.userService.newUser({
      ...user,
      password: passHas,
    });
    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'No se pudo crear el usuario',
      });
    return new SuccessResponse('Se creó con exito');
  }

  @Get('profile/:id')
  async getUserProfile(
    @Param('id') id: number,
  ): Promise<ResponseAPI<UserProfileResponse>> {
    const user = await this.userService.getUserById(id);

    if (!user)
      throw new ErrorResponseException({
        errorMessage: 'No existe el usuario',
      });
    const userResponse = new UserProfileResponse({
      email: user.email,
      username: user.username,
      image: user.image,
      rol: user.rol,
    });
    return new SuccessResponse(userResponse);
  }

  @UseGuards(JwtAuthGuard)
  @Put('profile/:id')
  async updateProfile(
    @Param('id') id: number,
    @Body() data: UpdateUserI,
  ): Promise<ResponseAPI<string>> {
    const res = await this.userService.updateUserData(id, data);
    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'No se pudo actualizar los datos del usuario.',
      });
    return new SuccessResponse('Perfil actualizado!');
  }

  @Get()
  async getAllUser() {
    const list = await this.userService.getAllUser();
    return new SuccessResponse({
      userList: list,
      totalUser: list.length,
    });
  }
  @Delete(':userId')
  async deleteUser(@Param('userId') userId: number) {
    const res = await this.userService.deleteUser(userId);
    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'no se pudo eliminar el usuario',
      });
    return new SuccessResponse('Usuario eliminado con exito');
  }
}
