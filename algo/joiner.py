import sys, os, re

incl = re.compile(r'#include[ \t]+"(.*)"')

    

def proc(filename):
    location = os.path.dirname(os.path.abspath(filename))
    header = "/* BEGIN " + filename + " */\n"
    footer = "/* END " + filename + " */\n"


    def repl(matchobj):
        path = os.path.join(location, matchobj.group(1))
        return proc(path)

    res = header
    with open(filename) as file:
        for line in file:
            line = re.sub(incl, repl, line)
            res = res + line

    res = res + footer
    return res


print (proc (sys.argv[1]))
