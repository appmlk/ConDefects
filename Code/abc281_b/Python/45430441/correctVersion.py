def solve(s: str) :
    """ You are given a string S consisting of uppercase English letters and digits. Determine whether
    S satisfies the following condition.
    S is a concatenation of the following characters and string in the order listed.
    1 - An uppercase English letter
    2 - A string of length  6 that is a decimal representation of an integer between 100000 and 999999, inclusive
    3 - An uppercase English letter
    """
    if len(s) != 8:
        return "No"
    number = ""
    if 'A' <= s[0] <= 'Z':
        for i in range(1, len(s) - 1):
            if '0' <= s[i] <= '9':
                number += s[i]
            else:
                return "No"
        if 100000 <= int(number) <= 999999:
            if 'A' <= s[-1] <= 'Z':
                return "Yes"
            else:
                return "No"
        else:
            return "No"
    else:
        return "No"


s = str(input())
print(solve(s))