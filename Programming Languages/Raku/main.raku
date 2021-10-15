use v6;
use lib '.';
use MyCode;

# Read the test program
my $data = slurp "program.txt";

say "The original program is";
say "--------------";
say $data;
say "--------------";

# Tokenize
my @tokens = getTokens($data);

say "Tokens are";
say "--------------";
for @tokens -> $token {
  say $token;
}
say "--------------";

# Balance 

say "Testing balance";
say "--------------";
say balance(@tokens);
say "--------------";

# Format 
my $pretty = format(@tokens);

say "Formatted the program is";
say "--------------";
say $pretty;
say "--------------";
