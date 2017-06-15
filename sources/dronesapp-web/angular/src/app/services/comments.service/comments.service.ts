import { Injectable } from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {CommentModel} from './comment.interface';
import {getFromObjectToObject} from "../../shared/common/common-methods";

@Injectable()
export class CommentsService {
  constructor(
    private requestService: RequestService
  ) { }

  formatCommentToPreview(comments) {
    return comments.map((comment) =>
      getFromObjectToObject(comment, 'account:firstName', 'account:lastName', 'id', 'comment', 'createdAt', 'account:photoURL'));
  }

  fetchComment(id: number|string) {
    return this.requestService.fetch('get', `/projects/${id}/comments`)
      .catch(() => []);
  }

  createComment(comment: CommentModel) {
    const data = Object.assign({
      comment: '',
      id: 0,
      projectId: 0
    }, comment);

    return this.requestService.fetch('post', '/comments', data);
  }

}
