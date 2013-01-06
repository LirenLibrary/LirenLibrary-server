describe('Liren server side admin page', function(){
    it("should redirect index.html to index.html#/", function(){
        browser().navigateTo('../../index.html');
        expect(browser().location().url()).toBe('/');
    });
});