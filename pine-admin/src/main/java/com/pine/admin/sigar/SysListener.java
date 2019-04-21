package com.pine.admin.sigar;

/**
 * @Author: Pine
 * @Date: 2019/4/6
 * @Email:771190883@qq.com
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


public class SysListener {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    public static String getData(@Autowired DataSourceProperties dataSourceProperties) throws UnknownHostException {
        SysListener sysListener = new SysListener();
        StringBuffer stringBuffer = new StringBuffer();

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        try {
            // System信息，从jvm获取
//            String property = new SysListener().property(dataSourceProperties);
//            System.out.println(property + "-");
            // cpu信息
            String cpu = cpu();
            System.out.println(cpu + "-");
            // 内存信息
            String memory = memory();
            System.out.println("-");
            // 操作系统信息
            os();
            System.out.println("-");
            // 用户信息
            who();
            System.out.println("-");
            // 文件系统信息
            file();
            System.out.println("-");
            // 网络信息
            net();
            System.out.println("-");
            // 以太网信息
            ethernet();
            System.out.println("-");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static String property(DataSourceProperties dataSourceProperties) throws UnknownHostException {
        SysListener sysListener = new SysListener();
        StringBuffer stringBuffer = new StringBuffer();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        stringBuffer.append("数据库地址:    " + dataSourceProperties.getUrl() + "\n");
        stringBuffer.append("数据库用户名:    " + dataSourceProperties.getUsername() + "\n");
        stringBuffer.append("数据库密码:    " + dataSourceProperties.getPassword() + "\n");
        stringBuffer.append("数据库驱动:    " + dataSourceProperties.getDriverClassName() + "\n");
        stringBuffer.append("用户名:    " + userName + "\n");
        stringBuffer.append("计算机名:    " + computerName + "\n");
        stringBuffer.append("计算机域名:    " + userDomain + "\n");
        stringBuffer.append("本地ip地址:    " + ip + "\n");
        stringBuffer.append("本地主机名:    " + addr.getHostName() + "\n");
        stringBuffer.append("JVM可以使用的总内存:    " + r.totalMemory() + "\n");
        stringBuffer.append("JVM可以使用的剩余内存:    " + r.freeMemory() + "\n");
        stringBuffer.append("JVM可以使用的处理器个数:    " + r.availableProcessors() + "\n");
        stringBuffer.append("Java的运行环境版本：    " + props.getProperty("java.version") + "\n");
        stringBuffer.append("Java的运行环境供应商：    " + props.getProperty("java.vendor") + "\n");
        stringBuffer.append("Java供应商的URL：    " + props.getProperty("java.vendor.url") + "\n");
        stringBuffer.append("Java的安装路径：    " + props.getProperty("java.home") + "\n");
        stringBuffer.append("Java的虚拟机规范版本：    " + props.getProperty("java.vm.specification.version") + "\n");
        stringBuffer.append("Java的虚拟机规范供应商：    " + props.getProperty("java.vm.specification.vendor") + "\n");
        stringBuffer.append("Java的虚拟机规范名称：    " + props.getProperty("java.vm.specification.name") + "\n");
        stringBuffer.append("Java的虚拟机实现版本：    " + props.getProperty("java.vm.version") + "\n");
        stringBuffer.append("Java的虚拟机实现供应商：    " + props.getProperty("java.vm.vendor") + "\n");
        stringBuffer.append("Java的虚拟机实现名称：    " + props.getProperty("java.vm.name") + "\n");
        stringBuffer.append("Java运行时环境规范版本：    " + props.getProperty("java.specification.version") + "\n");
        stringBuffer.append("Java运行时环境规范供应商：    " + props.getProperty("java.specification.vender") + "\n");
        stringBuffer.append("Java运行时环境规范名称：    " + props.getProperty("java.specification.name") + "\n");
        stringBuffer.append("Java的类格式版本号：    " + props.getProperty("java.class.version") + "\n");
        stringBuffer.append("Java的类路径：    " + props.getProperty("java.class.path") + "\n");
        stringBuffer.append("加载库时搜索的路径列表：    " + props.getProperty("java.library.path") + "\n");
        stringBuffer.append("默认的临时文件路径：    " + props.getProperty("java.io.tmpdir") + "\n");
        stringBuffer.append("一个或多个扩展目录的路径：    " + props.getProperty("java.ext.dirs") + "\n");
        stringBuffer.append("操作系统的名称：    " + props.getProperty("os.name") + "\n");
        stringBuffer.append("操作系统的构架：    " + props.getProperty("os.arch") + "\n");
        stringBuffer.append("操作系统的版本：    " + props.getProperty("os.version") + "\n");
        stringBuffer.append("文件分隔符：    " + props.getProperty("file.separator") + "\n");
        stringBuffer.append("路径分隔符：    " + props.getProperty("path.separator") + "\n");
        stringBuffer.append("行分隔符：    " + props.getProperty("line.separator") + "\n");
        stringBuffer.append("用户的账户名称：    " + props.getProperty("user.name") + "\n");
        stringBuffer.append("用户的主目录：    " + props.getProperty("user.home") + "\n");
        stringBuffer.append("用户的当前工作目录：    " + props.getProperty("user.dir") + "\n");
        return stringBuffer.toString();
    }

    public static String memory() throws SigarException {
        StringBuffer stringBuffer = new StringBuffer();

        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        // 内存总量
        stringBuffer.append("内存总量:    " + mem.getTotal() / 1024L + "K av");
        // 当前内存使用量
        stringBuffer.append("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
        // 当前内存剩余量
        stringBuffer.append("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
        Swap swap = sigar.getSwap();
        // 交换区总量
        stringBuffer.append("交换区总量:    " + swap.getTotal() / 1024L + "K av");
        // 当前交换区使用量
        stringBuffer.append("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
        // 当前交换区剩余量
        stringBuffer.append("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
        return stringBuffer.toString();
    }

    public static String cpu() throws SigarException {
        StringBuffer stringBuffer = new StringBuffer();

        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            CpuInfo info = infos[i];
            stringBuffer.append("第" + (i + 1) + "块CPU信息");
            stringBuffer.append("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
            stringBuffer.append("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
            stringBuffer.append("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
            stringBuffer.append("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
            printCpuPerc(cpuList[i]);
        }
        return stringBuffer.toString();
    }

    public static String printCpuPerc(CpuPerc cpu) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
        stringBuffer.append("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
        stringBuffer.append("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
        stringBuffer.append("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));//
        stringBuffer.append("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
        stringBuffer.append("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));// 总的使用率
        return stringBuffer.toString();

    }

    public static String os() {
        StringBuffer stringBuffer = new StringBuffer();

        OperatingSystem OS = OperatingSystem.getInstance();
        // 操作系统内核类型如： 386、486、586等x86
        stringBuffer.append("操作系统:    " + OS.getArch());
        stringBuffer.append("操作系统CpuEndian():    " + OS.getCpuEndian());//
        stringBuffer.append("操作系统DataModel():    " + OS.getDataModel());//
        // 系统描述
        stringBuffer.append("操作系统的描述:    " + OS.getDescription());
        // 操作系统类型
        // stringBuffer.append("OS.getName():    " + OS.getName());
        // stringBuffer.append("OS.getPatchLevel():    " + OS.getPatchLevel());//
        // 操作系统的卖主
        stringBuffer.append("操作系统的卖主:    " + OS.getVendor());
        // 卖主名称
        stringBuffer.append("操作系统的卖主名:    " + OS.getVendorCodeName());
        // 操作系统名称
        stringBuffer.append("操作系统名称:    " + OS.getVendorName());
        // 操作系统卖主类型
        stringBuffer.append("操作系统卖主类型:    " + OS.getVendorVersion());
        // 操作系统的版本号
        stringBuffer.append("操作系统的版本号:    " + OS.getVersion());
        return stringBuffer.toString();

    }

    public static String who() throws SigarException {
        StringBuffer stringBuffer = new StringBuffer();

        Sigar sigar = new Sigar();
        Who who[] = sigar.getWhoList();
        if (who != null && who.length > 0) {
            for (int i = 0; i < who.length; i++) {
                // stringBuffer.append("当前系统进程表中的用户名" + String.valueOf(i));
                Who _who = who[i];
                stringBuffer.append("用户控制台:    " + _who.getDevice());
                stringBuffer.append("用户host:    " + _who.getHost());
                // stringBuffer.append("getTime():    " + _who.getTime());
                // 当前系统进程表中的用户名
                stringBuffer.append("当前系统进程表中的用户名:    " + _who.getUser());
            }
        }
        return stringBuffer.toString();

    }

    public static String file() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            stringBuffer.append("分区的盘符名称" + i);
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            stringBuffer.append("盘符名称:    " + fs.getDevName());
            // 分区的盘符名称
            stringBuffer.append("盘符路径:    " + fs.getDirName());
            stringBuffer.append("盘符标志:    " + fs.getFlags());//
            // 文件系统类型，比如 FAT32、NTFS
            stringBuffer.append("盘符类型:    " + fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            stringBuffer.append("盘符类型名:    " + fs.getTypeName());
            // 文件系统类型
            stringBuffer.append("盘符文件系统类型:    " + fs.getType());
            FileSystemUsage usage = null;
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    // 文件系统总大小
                    usage = sigar.getFileSystemUsage(fs.getDirName());
                    stringBuffer.append(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
                    // 文件系统剩余大小
                    stringBuffer.append(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
                    // 文件系统可用大小
                    stringBuffer.append(fs.getDevName() + "可用大小: " + usage.getAvail() + "KB");
                    // 文件系统已经使用量
                    stringBuffer.append(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
                    double usePercent = usage.getUsePercent() * 100D;
                    // 文件系统资源的利用率
                    stringBuffer.append(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
                    stringBuffer.append(fs.getDevName() + "读出：    " + usage.getDiskReads());
                    stringBuffer.append(fs.getDevName() + "写入：    " + usage.getDiskWrites());
                    break;
                case 3:// TYPE_NETWORK ：网络
                    break;
                case 4:// TYPE_RAM_DISK ：闪存
                    break;
                case 5:// TYPE_CDROM ：光驱
                    break;
                case 6:// TYPE_SWAP ：页面交换
                    break;
                    default:
                        break;
            }

        }
        return stringBuffer.toString();

    }

    public static String net() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        Sigar sigar = new Sigar();
        String ifNames[] = sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            stringBuffer.append("网络设备名:    " + name);// 网络设备名
            stringBuffer.append("IP地址:    " + ifconfig.getAddress());// IP地址
            stringBuffer.append("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                stringBuffer.append("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            stringBuffer.append(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
            stringBuffer.append(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
            stringBuffer.append(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
            stringBuffer.append(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
            stringBuffer.append(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
            stringBuffer.append(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
            stringBuffer.append(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
            stringBuffer.append(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
        }
        return stringBuffer.toString();

    }

    public static String ethernet() throws SigarException {
        StringBuffer stringBuffer = new StringBuffer();

        Sigar sigar = null;
        sigar = new Sigar();
        String[] ifaces = sigar.getNetInterfaceList();
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            stringBuffer.append(cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
            stringBuffer.append(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            stringBuffer.append(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            stringBuffer.append(cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
            stringBuffer.append(cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            stringBuffer.append(cfg.getName() + "网卡类型" + cfg.getType());//
        }
        return stringBuffer.toString();
    }
}