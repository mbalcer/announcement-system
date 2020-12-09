import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';

const TOKEN_HEADER_KEY = 'Authorization';
const TOKEN_PREFIX = 'Bearer ';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private token: TokenStorageService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    this.token.isTokenExpired();
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, TOKEN_PREFIX + token)});
    }
    return next.handle(authReq);
  }
}
