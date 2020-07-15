import {
  Body,
  Controller,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import {
  FailResponse,
  ResponseAPI,
  SuccessResponse,
} from 'src/common/dto/response.dto';
import { UserProfileResponse } from '../common/dto/profile.dto';
import { UpdateUserI } from './data/interfaces/user.interface';
import UserService from './user.service';

@Controller('users')
export default class UserController {
  constructor(private readonly userService: UserService) {}

  @Get('profile/:id')
  async getUserProfile(
    @Param('id') id: number,
  ): Promise<ResponseAPI<UserProfileResponse>> {
    const user = await this.userService.getUserById(id);

    if (!user) return new FailResponse('No existe el usuario');
    const userResponse = new UserProfileResponse({
      email: user.email,
      username: user.username,
      image: user.image,
    });
    return new SuccessResponse(userResponse);
  }

  @Put('profile/:id')
  async updateProfile(
    @Param('id') id: number,
    @Body() data: UpdateUserI,
  ): Promise<ResponseAPI<string>> {
    await this.userService.updateUserData(id, data);
    return new SuccessResponse('Perfil actualizado!');
  }
}
