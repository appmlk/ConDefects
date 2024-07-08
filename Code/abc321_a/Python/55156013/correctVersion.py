N = input()


def is321():
    a = int(N[0])
    for n in N[1:]:
        if int(n) >= a:
            return "No"
        a = int(n)
    return "Yes"


print(is321())
