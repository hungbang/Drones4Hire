import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, ActivatedRoute} from '@angular/router';

import {CommentsService} from '../../services/comments.service/comments.service';

@Injectable()
export class CommentsResolve implements Resolve<any> {

  constructor(
    private commentsService: CommentsService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const projectId = route.params['projectId'];

    return this.commentsService.fetchComment(projectId);
  }
}
