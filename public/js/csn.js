// dashboard
function refreshMessagelist() {
  $.getJSON('/users/me/messages.json', function(messages) {
    $('#pms ul').empty();
    $.each(messages.messages, function(key, message) {
      var li = $('<li>');
      li.append(
        $('<a>')
        .attr('href', '/users/me/messages/'+message.id)
        .text(message.title)
      )
      .append(
        ' from '
      )
      .append(
        $('<a>')
        .attr('href', '/users/'+message.author)
        .text(message.author)
      )
      
      if(message.new) {
        li.append(' ').append(
          $('<span>')
          .attr('class', 'label label-info')
          .text('new')
        );
      }
      
      $('#pms ul')
      .append(
        li
      );
    });
  })
  .complete(setTimeout(refreshMessagelist, 15000));
  return true;
}

// init function when dom is ready
$(function() {
  $(ready).each(function(key) {
    window[ready[key]]();
  });
});
