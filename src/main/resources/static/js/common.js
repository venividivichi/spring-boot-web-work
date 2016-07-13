/**
 * Created by Nazar on 27.06.2016.
 */
$(document).ready(function() {
    $('.dropdown-toggle').on('click', function () {
        if($(this).hasClass('active')){
            if($(this).attr('aria-expanded', 'true')) {
                $(this).next($('.dropdown-menu')).hide();
                $(this).attr('aria-expanded', 'false');
                $(this).parent().removeClass('open');
            }
            $(this).removeClass('active');
        } else {
            if($(this).attr('aria-expanded', 'false')){
                $(this).next($('.dropdown-menu')).show();
                $(this).attr('aria-expanded', 'true');
                $(this).parent().addClass('open');
            }
            $(this).addClass('active');
        }
    });
});