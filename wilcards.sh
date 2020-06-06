# matches one or more characters,use * ,for example
# *.txt

# matches one  character,use ?, for example
# a?.txt
#a??.txt

# character class []
# matches exactly one character included bw the brackets
# ca[nt]*  will match cat,can,candy,catch

# character class [!]
# will not match the characters included bw the brackets
#[!aeiou]* will match baseball,cricket

# character range [a-c] [1-4]
# use 2 characters seperated by a hyphen to create a range in a character class
# [a-d]* matches all files that starts with a,b,c,d
# [1-4]* matches all files that starts with 1,2,3,4

# predefined named character classes
# [[:alpha:]]
# [[:alnum:]]
# [[:lower:]]
# [[:upper:]]