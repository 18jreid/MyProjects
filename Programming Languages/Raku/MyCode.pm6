# CS4700_HW3
# Justin Reid
# A02276642
use v6;
unit module MyCode;

sub getTokens ($program) is export {
  # Recursively adds values from $text into token list
  # Returns an array of tokens
  sub generateTokens($text, @resultsList) {
    my $newString = "";
    if substr($text, 0..0) ~~ m/ ";" / {
      if $text ~~ m/ "\n" / {
        $newString = $/.postmatch;
      }
      else {
        $newString = "";
      }
    }
    elsif substr($text, 0..0) ~~ m/ " " / {
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/ "\n" / {
      $newString = substr($text, 1..$text.chars);
    } 
    elsif substr($text, 0..0) ~~ m/ "(" / {
      @resultsList.append("LPAREN: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/ ")" / {
      @resultsList.append("RPAREN: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/ "*" / {
      @resultsList.append("MULTIPLICATION: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/ "+" / {
      @resultsList.append("ADDITION: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/ "-" / {
      @resultsList.append("SUBTRACTION: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/ "/" / {
      @resultsList.append("DIVISION: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    } 
    elsif substr($text, 0..0) ~~ m/ \d / {
      @resultsList.append("INTEGER: " ~ $/);
      $newString = substr($text, 1..$text.chars);
    }
    elsif substr($text, 0..0) ~~ m/(<:L>) / {
      if $text ~~ m/ (<:!L>) / {
        @resultsList.append("IDENTIFIER: " ~ $/.prematch);
        $newString = $/ ~ $/.postmatch;
      }
    }
  
    #Recursion ends when $text is empty
    if $newString.chars > 0 {
    return generateTokens($newString, @resultsList);
    }
    else {
      return @resultsList;
    }
  }

  my @results = ();
  
  my @newData = generateTokens($program, @results);

  return @newData;
}

# Goes through all tokens, and determines if the program is balanced (valid).
# Input: tokens
# Output: boolean
sub balance (@tokens) is export {
    # Count to keep track of parenthesis
    my $count = 0;
    for @tokens -> $token {
        if ($token.contains("RPAREN: ")) {
            $count -= 1;
        }

        if ($token.contains("LPAREN: ")) {
            $count += 1;
        }
    }

    if ($count == 0) {
        return True;
    } else {
        return False;
    }
}

# Given a tokens description, return the actual token.
# Input:        A $data string containing the token description
# Output:       A $returnValue string containing the token itself
sub getTokenCharacter($data) is export {
    # split the data based on colon
    # colon is used in the Tokenize output
    my @dataSplit = split(/\:/, $data, :skip-empty, :v);

    my $returnValue = "";

    # use this to track once we've found the colon
    my $foundColon = False;

    for @dataSplit -> $element {
        if $foundColon == True {
            # after finding first colon, append all data to return value
            $returnValue = $returnValue ~ $element;
        }
        elsif $element eq ":" {
            # once we find first colon, mark everything after that as relevant
            $foundColon = True;
        }
        else {
            # do nothing before finding first colon
        }
    }
    return $returnValue;
}

# Formats a list of tokens the orginal function and makes it look pretty. Uses the helper functino getTokenCharacter()
# Input: Token list
# Output: pretty printed tokens
sub format (@tokens) is export {
    my @results = "\n";
    my $tabs = "";
    for @tokens -> $token {
        if ($token.contains("LPAREN: ")) {
            @results.append($tabs ~ ")\n");
            $tabs = $tabs ~ "  ";
        }
        if ($token.contains("RPAREN: ")) {
            if ($tabs.chars > 0) {
            $tabs = substr($tabs, 2..*);
            }
            @results.append($tabs ~ ")\n");
        }
        if ($token.contains("MULTIPLICATION: ")) {
            @results.append($tabs ~ getTokenCharacter($token) ~ "\n");
        }
        if ($token.contains("ADDITION: ")) {
            @results.append($tabs ~ getTokenCharacter($token) ~ "\n");
        }
        if ($token.contains("SUBTRACTION: ")) {
            @results.append($tabs ~ getTokenCharacter($token) ~ "\n");
        }
        if ($token.contains("DIVISION: ")) {
            @results.append($tabs ~ getTokenCharacter($token) ~ "\n");
        }
        if ($token.contains("IDENTIFIER: ")) {
            @results.append($tabs ~ getTokenCharacter($token) ~ "\n");
        }
        if ($token.contains("INTEGER: ")) {
            @results.append($tabs ~ getTokenCharacter($token) ~ "\n");
        }
    }

    return @results;
}
