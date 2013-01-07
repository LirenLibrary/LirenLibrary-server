describe('Liren server side admin page', function () {
    it("should redirect index.html to index.html#/", function () {
        //when
        browser().navigateTo('../../index.html');

        //then
        expect(browser().location().url()).toBe('/');
    });

    describe('should load all the donations to second nav', function () {
        it('should have more than one donations', function () {
            //when
            browser().navigateTo('../../index.html');

            //then
            expect(repeater('.book-order li').count()).toBeGreaterThan(1);
        });
    });

    describe('should redirect to books lists by click donation', function () {
        it('should jump to the books of first donation', function () {
            //given
            browser().navigateTo('../../index.html');

            //when
            element('.book-order li a:first').click();

            //then
            expect(browser().location().url()).toMatch(/\/donations\/\d/);
            expect(repeater('tr').count()).toBeGreaterThan(1);
        });
    });
});