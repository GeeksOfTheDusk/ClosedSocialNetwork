// dashboard
function refreshMessagelist() {
  var count = 6;
  $.getJSON('/users/me/pmessages.json', { count: count }, function(data) {
    // create hint to more messages
    if(data.messages.length >= count) {
      if($('#pms p a+a').size() <= 0) {
        $('#pms p')
        .append(' ')
        .append(
          $('<a>')
          .attr('class', 'btn')
          .attr('href', '/users/me/pmessages')
          .html('List more &raquo;')
        )
      }
    }else {
      $('#pms p a+a').remove();
    }
    
    // list pms
    $('#pms ul').empty();
    $.each(data.messages, function(key, message) {
      // handle hint to more messages
      if(key + 1 == count) {
        return false;
      }
      
      var li = $('<li>');
      var user = ''
      if(message.authorId >= 0) {
        user = $('<a>').attr('href', '/users/'+message.author)
                       .text(message.author)
      } else {
        user = message.author
      }

      li.append(
        $('<a>')
        .attr('href', '/users/me/pmessages/'+message.id)
        .text(message.title)
      )
      .append(
        ' from '
      )
      .append(user)
      
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
