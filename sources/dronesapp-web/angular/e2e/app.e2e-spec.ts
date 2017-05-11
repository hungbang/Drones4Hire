import { ArliansPage } from './app.po';

describe('arlians App', () => {
  let page: ArliansPage;

  beforeEach(() => {
    page = new ArliansPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
