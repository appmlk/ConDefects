N = input()


def is321():
    a = int(N[0])
    for n in N[1:]:
        a = int(n)
        if int(n) >= a:
            return "No"
    return "Yes"


print(is321())
