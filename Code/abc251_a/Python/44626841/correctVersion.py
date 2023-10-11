#Six characters
def solution(string):
    if len(string) == 1:
        return string * 6
    elif len(string) == 2:
        return string * 3
    else:
        return string * 2

string = str(input("")).lower()
print(solution(string))