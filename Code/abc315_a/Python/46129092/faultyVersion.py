def remove_str(input_str):
    result = input_str.replace('a', '').replace('e', '').replace('o', '')
    return result

s = input()
s_result = remove_str(s)
print(s_result)